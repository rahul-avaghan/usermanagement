import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { UserService } from "../user.service";
import { User } from "../user";
@Component({
  selector: "app-createuser",
  templateUrl: "./createuser.component.html",
  styleUrls: ["./createuser.component.css"]
})
export class CreateUserComponent implements OnInit {
  user: User;
  showDetails = true;
  constructor(private userService: UserService, private router: Router) {
    this.user = new User();
    this.showDetails = true;
  }

  ngOnInit() {}
  onCancel() {
    this.user = new User();
    this.showDetails = false;
    this.router.navigate([""]);
  }
  /**
   *
   * @param userInfo
   */
  onSaveUserDetails(userInfo) {
    this.userService.createUserInfo(userInfo);
    this.showDetails = false;
    this.router.navigate([""]);
  }
}
