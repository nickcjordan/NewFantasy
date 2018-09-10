import {NgModule, Injectable} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {UserDetailComponent} from './user-detail/user-detail.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {UserPreviewComponent} from './user-preview/user-preview.component';
import { LoginComponent } from './login/login.component';
import { HomeComponent } from './home';
import { RegisterComponent } from './register';
import { AuthGuard } from './_guards';

const routes: Routes = [
	{ path: 'admin', component: HomeComponent, canActivate: [AuthGuard] },
	{path: '', redirectTo: '/dashboard', pathMatch: 'full'},
	{path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard] },
	{path: 'userPreview/:id', component: UserPreviewComponent},
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
   
    { path: '**', redirectTo: '' }  // otherwise redirect to home
];


@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule]
})
export class AppRoutingModule {}