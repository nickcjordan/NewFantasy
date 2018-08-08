import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';
import {HttpClientInMemoryWebApiModule} from 'angular-in-memory-web-api';
import {HttpClientModule} from '@angular/common/http';

import {InMemoryDataService} from './service/in-memory-data.service';
import {AppComponent} from './app.component';
import {UserDetailComponent} from './user-detail/user-detail.component';
import {MessagesComponent} from './messages/messages.component';
import {AppRoutingModule} from './/app-routing.module';
import {DashboardComponent} from './dashboard/dashboard.component';
import { UserSearchComponent } from './user-search/user-search.component';
import { NavComponent } from './common/nav/nav.component';
import { UserPreviewComponent } from './users/user-preview/user-preview.component';
import { UserEditComponent } from './users/user-edit/user-edit.component';
import {NgbModule} from '@ng-bootstrap/ng-bootstrap';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';

@NgModule({
	declarations: [
		AppComponent,
		UserDetailComponent,
		MessagesComponent,
		DashboardComponent,
		UserSearchComponent,
		NavComponent,
		UserPreviewComponent,
		UserEditComponent,
		HomeComponent,
		LoginComponent
	],
	imports: [
		BrowserModule,
		HttpClientModule,
		//HttpClientInMemoryWebApiModule.forRoot( InMemoryDataService, {dataEncapsulation: false} ),
		FormsModule,
		AppRoutingModule,
		NgbModule.forRoot()
	],
	providers: [],
	bootstrap: [AppComponent]
})
export class AppModule {}