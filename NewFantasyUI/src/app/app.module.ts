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
import { AuthService } from "./service/auth.service";
import { HTTP_INTERCEPTORS } from "@angular/common/http";
import { HttpHandler } from "@angular/common/http";
import { HttpRequest } from "@angular/common/http";
import { HttpInterceptor } from "@angular/common/http";

import { AlertComponent } from './_directives';
import { AuthGuard } from './_guards';
import { JwtInterceptor, ErrorInterceptor } from './_helpers';
import { AlertService, AuthenticationService, UserCredentialService } from './_services';
import { HomeComponent } from './home';
import { LoginComponent } from './login';
import { RegisterComponent } from './register';
import { ReactiveFormsModule }    from '@angular/forms';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';


// used to create fake backend
import { fakeBackendProvider } from './_helpers';
import { PlayerEditPopoverComponent } from './users/user-edit/player-edit-popover/player-edit-popover.component';


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
		LoginComponent,
		AppComponent,
        AlertComponent,
        HomeComponent,
        RegisterComponent,
        PlayerEditPopoverComponent
	],
	imports: [
		BrowserModule,
		HttpClientModule,
		//HttpClientInMemoryWebApiModule.forRoot( InMemoryDataService, {dataEncapsulation: false} ),
		ReactiveFormsModule,
		AppRoutingModule,
		NgbModule,
		NgbModule.forRoot(),
		FontAwesomeModule
	],
	providers: [
//		AuthService 
		{ provide: HTTP_INTERCEPTORS, useClass: XhrInterceptor, multi: true },
		AuthGuard,
        AlertService,
        AuthenticationService,
        UserCredentialService,
        { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
        { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true },

        // provider used to create fake backend
        fakeBackendProvider
	],
	bootstrap: [AppComponent]
})
export class AppModule {}

