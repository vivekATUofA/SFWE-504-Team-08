import { Component } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { AuthService } from 'src/app/core/services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent {
  loading = false;
  errorMessage = '';
  successMessage = '';

  form = this.fb.group({
    fullName: ['', Validators.required],
    email: ['', [Validators.required, Validators.email]],
    password: ['', [Validators.required, Validators.minLength(6)]],
    confirmPassword: ['', Validators.required]
  });

  constructor(private fb: FormBuilder, private auth: AuthService, private router: Router) {}

  register(): void {
    this.errorMessage = '';
    this.successMessage = '';

    if (this.form.invalid) {
      this.errorMessage = 'Please fill the form correctly.';
      return;
    }

    const { password, confirmPassword } = this.form.value;
    if (password !== confirmPassword) {
      this.errorMessage = 'Passwords do not match.';
      return;
    }

    this.loading = true;
    const payload = {
      fullName: this.form.value.fullName,
      email: this.form.value.email,
      password: this.form.value.password
    };

    this.auth.register(payload).subscribe({
      next: () => {
        this.loading = false;
        this.successMessage = 'Registration successful. Redirecting to login...';
        setTimeout(() => this.router.navigate(['/auth/login']), 1400);
      },
      error: (err) => {
        this.loading = false;
        this.errorMessage = err?.error?.message || 'Registration failed';
      }
    });
  }
}
