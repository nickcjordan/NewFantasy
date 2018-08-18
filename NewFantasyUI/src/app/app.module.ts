import {BrowserModule} from '@angular/platform-browser';
import {NgModule, Injectable} from '@angular/core';
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
import { LoginComponent } from './login/login.component';
import { AuthService } from "./service/auth.service";
import { HTTP_INTERCEPTORS } from "@angular/common/http";
import { HttpHandler } from "@angular/common/http";
import { HttpRequest } from "@angular/common/http";
import { HttpInterceptor } from "@angular/common/http";

@Injectable()
export class XhrInterceptor implements HttpInterceptor {
	intercept(req: HttpRequest<any>, next: HttpHandler) {
		return next.handle(req.clone({ headers: req.headers.set('X-Requested-With', 'XMLHttpRequest') }));
	}
}

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
	providers: [AuthService, { provide: HTTP_INTERCEPTORS, useClass: XhrInterceptor, multi: true }],
	bootstrap: [AppComponent]
})
export class AppModule {}

