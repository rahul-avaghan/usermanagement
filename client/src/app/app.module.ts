import { BrowserModule } from "@angular/platform-browser";
import { NgModule } from "@angular/core";
import { AppComponent } from "./app.component";
import { MainviewComponent } from "./mainview/mainview.component";
import { UserlistComponent } from "./userlist/userlist.component";
import { UserdetailsviewComponent } from "./userdetailsview/userdetailsview.component";
import { NavigationComponent } from "./navigation/navigation.component";
import { CreateUserComponent } from "./createuser/createuser.component";
import { UserComponent } from "./user/user.component";
import { RouterModule, Routes } from "@angular/router";
import {
  MatAutocompleteModule,
  MatBadgeModule,
  MatBottomSheetModule,
  MatButtonModule,
  MatButtonToggleModule,
  MatCardModule,
  MatCheckboxModule,
  MatChipsModule,
  MatDatepickerModule,
  MatDialogModule,
  MatDividerModule,
  MatExpansionModule,
  MatGridListModule,
  MatIconModule,
  MatInputModule,
  MatListModule,
  MatMenuModule,
  MatNativeDateModule,
  MatPaginatorModule,
  MatProgressBarModule,
  MatProgressSpinnerModule,
  MatRadioModule,
  MatRippleModule,
  MatSelectModule,
  MatSidenavModule,
  MatSliderModule,
  MatSlideToggleModule,
  MatSnackBarModule,
  MatTabsModule,
  MatToolbarModule,
  MAT_DIALOG_DEFAULT_OPTIONS
} from "@angular/material";
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { HttpClientModule } from "@angular/common/http";
import { DeleteconfirmationComponent } from "./deleteconfirmation/deleteconfirmation.component";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { APP_BASE_HREF, Location } from "@angular/common";
@NgModule({
  exports: [
    MatAutocompleteModule,
    MatBadgeModule,
    MatBottomSheetModule,
    MatButtonModule,
    MatButtonToggleModule,
    MatCardModule,
    MatCheckboxModule,
    MatChipsModule,
    MatDatepickerModule,
    MatDialogModule,
    MatDividerModule,
    MatExpansionModule,
    MatGridListModule,
    MatIconModule,
    MatInputModule,
    MatListModule,
    MatMenuModule,
    MatNativeDateModule,
    MatPaginatorModule,
    MatProgressBarModule,
    MatProgressSpinnerModule,
    MatRadioModule,
    MatRippleModule,
    MatSelectModule,
    MatSidenavModule,
    MatSliderModule,
    MatSlideToggleModule,
    MatSnackBarModule,
    MatTabsModule,
    MatToolbarModule
  ],
  declarations: []
})
export class MaterialModule {}
const routes: Routes = [
  {
    path: "",
    pathMatch: "full", //default
    redirectTo: "userlist"
  },
  {
    path: "userlist",
    component: MainviewComponent,
    children: [
      { path: "user/:id", component: UserdetailsviewComponent },
      { path: "createuser", component: CreateUserComponent }
    ]
  }
];
@NgModule({
  declarations: [
    AppComponent,
    MainviewComponent,
    UserlistComponent,
    UserdetailsviewComponent,
    NavigationComponent,
    CreateUserComponent,
    UserComponent,
    DeleteconfirmationComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    MaterialModule,
    RouterModule.forRoot(routes, { onSameUrlNavigation: "reload" })
  ],
  entryComponents: [DeleteconfirmationComponent],
  providers: [
    { provide: MAT_DIALOG_DEFAULT_OPTIONS, useValue: { hasBackdrop: true } },
    { provide: APP_BASE_HREF, useValue: window["_app_base"] || "/" }
  ],
  bootstrap: [AppComponent]
})
export class AppModule {}
