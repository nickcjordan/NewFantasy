import {NgModule, Injectable} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {UserDetailComponent} from './user-detail/user-detail.component';
import {DashboardPageComponent} from './dashboard-page/dashboard-page.component';
import {WaiverWirePageComponent} from './waiver-wire-page/waiver-wire-page.component';
import {UserPreviewPageComponent} from './user-preview-page/user-preview-page.component';
import {ModifierMarketplacePageComponent} from './modifier-marketplace-page/modifier-marketplace-page.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home';
import { RegisterComponent } from './register';
import { AuthGuard } from './_guards';

const routes: Routes = [
	{ path: 'admin', component: HomeComponent, canActivate: [AuthGuard] },
	{ path: '', redirectTo: '/dashboard', pathMatch: 'full'},
	
	{ path: 'dashboard', component: DashboardPageComponent, canActivate: [AuthGuard] },
	{ path: 'userPreview/:id', component: UserPreviewPageComponent},
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
	{ path: 'players', component: WaiverWirePageComponent, canActivate: [AuthGuard]  },
	{ path: 'marketplace', component: ModifierMarketplacePageComponent, canActivate: [AuthGuard]  },
   
    { path: '**', redirectTo: '' }  // otherwise redirect to home
];


@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule]
})
export class AppRoutingModule {}