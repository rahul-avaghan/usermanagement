import { Component, OnInit, EventEmitter, Output } from "@angular/core";
import { ActivatedRoute, Router } from "@angular/router";
import { UserService } from "../user.service";
import { User } from "../user";
import { FormsModule } from "@angular/forms";

@Component({
  selector: "app-userdetailsview",
  templateUrl: "./userdetailsview.component.html",
  styleUrls: ["./userdetailsview.component.css"]
})
export class UserdetailsviewComponent implements OnInit {
  id: string;
  showDetails = false;
  routeObserver;
  user: User;
  oldUserDetails: User;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private userService: UserService
  ) {
    this.routeObserver = this.route.params.subscribe(params => {
      this.id = params["id"];
      if (this.id) {
        this.userService.getUserById(this.id).subscribe(item => {
          if (item) {
            this.oldUserDetails = Object.assign({}, item);
            this.user = item;
            this.showDetails = true;
          } else {
            this.router.navigate([""]);
          }
        });
      }
    });
  }

  ngOnInit() {}
  // tslint:disable-next-line:use-life-cycle-interface
  ngOnDestroy() {
    this.routeObserver.unsubscribe();
  }

  /**
   * canecl current operation
   */
  onCancel() {
    this.user = JSON.parse(JSON.stringify(this.oldUserDetails));
    this.onUpdateDetails(this.oldUserDetails);
    this.showDetails = false;
    this.router.navigate([""]);
  }
  /**
   *  update information on edit or cancel
   * @param userInfo user infor to be updated on edit/cancel
   */
  onUpdateDetails(userInfo) {
    this.userService.updateUserInfo(userInfo);
    this.showDetails = false;
  }
}
