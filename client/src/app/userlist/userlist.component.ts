import { Component, OnInit } from "@angular/core";
import { UserService } from "../user.service";
import { Input, SimpleChanges } from "@angular/core";

@Component({
  selector: "app-userlist",
  templateUrl: "./userlist.component.html",
  styleUrls: ["./userlist.component.css"]
})
export class UserlistComponent implements OnInit {
  userList: any[];

  @Input() searchInput: string;

  constructor(private userService: UserService) {
    this.getUserDetails();
  }

  getUserDetails() {
    this.userService
      .getUsers()
      .subscribe(userList => (this.userList = userList));
  }

  ngOnInit() {
    this.userService.currentList.subscribe(m => (this.userList = m));
  }
  /**
   *
   * @param changes changed input values
   */
  ngOnChanges(changes: SimpleChanges) {
    if (changes.searchInput.currentValue) {
      this.userService
        .searchUser(changes.searchInput.currentValue)
        .subscribe(k => (this.userList = k));
    } else if (
      !changes.searchInput.currentValue &&
      changes.searchInput.previousValue
    ) {
      this.getUserDetails();
    }
  }
}
