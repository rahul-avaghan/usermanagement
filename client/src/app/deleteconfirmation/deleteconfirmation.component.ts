import { Component, OnInit, Inject } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
@Component({
  selector: 'app-deleteconfirmation',
  templateUrl: './deleteconfirmation.component.html',
  styleUrls: ['./deleteconfirmation.component.css']
})
export class DeleteconfirmationComponent implements OnInit {

  constructor(public dialogRef: MatDialogRef<DeleteconfirmationComponent>) { }

  ngOnInit() {
  }

  onYes(){
    this.dialogRef.close('Yes');
  }

  onNo(){
    this.dialogRef.close();
  }

}
