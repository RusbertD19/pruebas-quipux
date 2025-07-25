// src/app/app.config.ts
import { provideRouter, Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { ListsComponent } from './lists/lists.component';

const routes: Routes = [
  { path: '', component: LoginComponent },
  { path: 'lists', component: ListsComponent }
];

export const appConfig = [
  provideRouter(routes)
];
