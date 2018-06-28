import { Injectable } from "@angular/core";
import { User } from "./user";
import { Observable, of, BehaviorSubject } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { HttpHeaders } from "@angular/common/http";
import { catchError, retry } from "rxjs/operators";
import * as _ from "lodash";

@Injectable({
  providedIn: "root"
})
export class UserService {
  userList = [];
  configUrl = "/usermanagement/v1/user";
  /**
   * behavior subject for relatime change
   */
  private userSource = new BehaviorSubject<any>(this.userList);
  currentList = this.userSource.asObservable();
  httpOptions = {
    headers: new HttpHeaders({
      "Content-Type": "application/json"
    })
  };

  constructor(private http: HttpClient) {}
  /** get all user details*/
  getUsers(): Observable<User[]> {
    this.http.get<User[]>(this.configUrl).subscribe(m => {
      this.userList = m;
      this.userSource.next(this.userList);
    });
    return of(this.userList);
  }
  /**
   *To fetch a pertiular user details
   * @param id id of the user to fetch details
   */
  getUserById(id): Observable<User> {
    return this.http.get<User>(this.configUrl + "/" + id);
  }
  /**
   * update user details
   * @param user user information to be updated
   */
  updateUserInfo(user): void {
    this.http.put<User>(this.configUrl, user, this.httpOptions).subscribe(m => {
      const index = this.getLocalUserIndex(user.id);
      if (index > 0) {
        this.userList[index] = user;
        this.userSource.next(this.userList);
      }
    });
  }
  /**
   * Creates user on the server
   * @param user user info to be created
   */
  createUserInfo(user): void {
    user.photo = this.resolvePictureName(user);
    this.http
      .post<User>(this.configUrl, user, this.httpOptions)
      .subscribe((t: User) => {
        this.userList.push(t);
        this.userSource.next(this.userList);
      });
  }

  /**
   * Deletes the user information
   * @param id id of the user to be deleted
   */
  deleteUser(id: number): void {
    this.http
      .delete<User>(this.configUrl + "/" + id, this.httpOptions)
      .subscribe(m => {
        const index = this.getLocalUserIndex(id);
        if (index > 0) {
          this.userList.splice(index);
          this.userSource.next(this.userList);
        }
      });
  }
  searchUser(query: string): Observable<any[]> {
    const q = query.toLowerCase();
    const filteredList = _.filter(this.userList, (t: User) => {
      if (
        t.firstName.toLowerCase().indexOf(q) > -1 ||
        t.lastName.toLowerCase().indexOf(q) > -1 ||
        t.userName.toLowerCase().indexOf(q) > -1
      ) {
        return t;
      }
    });

    return of(filteredList);
  }

  /**generic error */
  handleError(message: string): void {
    console.log(message);
  }

  /**
   * Assign a random picture for user
   * @param user user data
   */
  resolvePictureName(user: User) {
    return user.male
      ? "male/m (" + Math.floor(Math.random() * Math.floor(6)) + ").png"
      : "female/f (" + Math.floor(Math.random() * Math.floor(6)) + ").png";
  }

  getLocalUserIndex(id) {
    return _.findIndex(this.userList, (t: User) => t.id === id);
  }
}
