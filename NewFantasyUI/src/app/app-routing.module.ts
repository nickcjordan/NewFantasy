import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {UserDetailComponent} from './user-detail/user-detail.component';
import {DashboardComponent} from './dashboard/dashboard.component';
import {UserPreviewComponent} from './users/user-preview/user-preview.component';

const routes: Routes = [
	{path: '', redirectTo: '/dashboard', pathMatch: 'full'},
//	{path: 'detail/:id', component: UserDetailComponent},
	{path: 'dashboard', component: DashboardComponent},
//	{path: 'users/:id', component: UsersComponent},
	{path: 'userPreview/:id', component: UserPreviewComponent}
];


@NgModule({
	imports: [RouterModule.forRoot(routes)],
	exports: [RouterModule]
})
export class AppRoutingModule {}