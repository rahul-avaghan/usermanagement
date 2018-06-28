import { Component, OnInit, Input } from "@angular/core";
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from "@angular/material";
import { DeleteconfirmationComponent } from "../deleteconfirmation/deleteconfirmation.component";
import { UserService } from "../user.service";
import { User } from "../user";

@Component({
  selector: "app-user",
  templateUrl: "./user.component.html",
  styleUrls: ["./user.component.css"]
})
export class UserComponent implements OnInit {
  @Input() user: User;

  DIALOG_WIDTH: number;
  DIALOG_CONFIMATION_SUCCESS = "Yes";
  selectedUserID: number;
  isDeleted = false;

  constructor(public dialog: MatDialog, private userSerivce: UserService) {}

  ngOnInit() {}

  /**
   * to select a user
   */
  selectUser() {
    this.selectedUserID = this.user.id;
  }

  /**
   * To delete card from the UI
   * @param event click event from delete button
   */
  onDelete(event) {
    event.stopPropagation();
    // ask for confirmation before deletion
    let dialogRef = this.dialog.open(DeleteconfirmationComponent, {
      width: this.DIALOG_WIDTH + "px"
    });

    dialogRef.afterClosed().subscribe(feedback => {
      if (feedback === this.DIALOG_CONFIMATION_SUCCESS) {
        this.isDeleted = true;
        setTimeout(() => {
          this.userSerivce.deleteUser(this.user.id);
        }, 300);
      }
    });
  }
}
