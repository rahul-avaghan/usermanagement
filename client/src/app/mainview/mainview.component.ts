import { Component, OnInit } from "@angular/core";
import { UserService } from "./../user.service";

@Component({
  selector: "app-mainview",
  templateUrl: "./mainview.component.html",
  styleUrls: ["./mainview.component.css"]
})
export class MainviewComponent implements OnInit {
  searchInput = "";
  constructor(private userService: UserService) {}

  ngOnInit() {}
}
