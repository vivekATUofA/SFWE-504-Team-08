import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { AuthService } from 'src/app/core/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent {
  loading = false;
  errorMessage = '';

  form = this.fb.group({
    email: ['', [Validators.required, Validators.email]],
    password: ['', Validators.required]
  });

  constructor(private fb: FormBuilder, private auth: AuthService, private router: Router) {}

  login(): void {
    this.errorMessage = '';
    if (this.form.invalid) {
      this.errorMessage = 'Please enter valid email and password.';
      return;
    }

    this.loading = true;

    this.auth.login(this.form.value).subscribe({
      next: () => {
        this.loading = false;
        this.router.navigate(['/']); // change to dashboard if you have one
      },
      error: (err) => {
        this.loading = false;
        this.errorMessage = err?.error?.message || 'Invalid email or password';
      }
    });
  }
}
