import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './appRoutingModule';
import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { ListsComponent } from './lists/lists.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ListsComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,            
    HttpClientModule,       
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
