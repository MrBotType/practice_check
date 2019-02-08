import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-signupform',
  templateUrl: './signupform.component.html',
  styleUrls: ['./signupform.component.css']
})
export class SignupformComponent implements OnInit {

  constructor(private fb: FormBuilder) { }

  signupForm = this.fb.group({
    userName: ['',Validators.compose([Validators.required,Validators.minLength(3),Validators.maxLength(20)])],
    email: ['',Validators.compose([Validators.required,Validators.minLength(8),Validators.maxLength(50)])],
    password: ['',Validators.compose([Validators.required,Validators.minLength(3),Validators.maxLength(20)])],
    confirmpassword: ['',Validators.compose([Validators.required,Validators.minLength(3),Validators.maxLength(20)])]
  });
  signupData = this.fb.group({
    userName: ['',Validators.compose([Validators.required,Validators.minLength(3),Validators.maxLength(20)])],
    email: ['',Validators.compose([Validators.required,Validators.minLength(8),Validators.maxLength(50)])],
    password: ['',Validators.compose([Validators.required,Validators.minLength(3),Validators.maxLength(20)])]
  });
  signupJson;

  ngOnInit() {
  }

  save(){
    console.log("save button clicked");
    console.log(this.signupForm.value);
    this.signupJson={
      "userName" : this.signupForm.value.userName,
      "email" : this.signupForm.value.email,
      "password" : this.signupForm.value.password
    }
    console.log("signUpJson");
    console.log(this.signupJson);
    this.signupData.patchValue(this.signupJson);
    console.log("signUpData");
    console.log(this.signupData.value);
  }

}
