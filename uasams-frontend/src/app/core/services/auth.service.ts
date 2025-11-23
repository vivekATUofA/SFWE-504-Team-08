import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { tap } from 'rxjs/operators';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private baseUrl = `${environment.apiBaseUrl}/auth`;

  constructor(private http: HttpClient) {}

  login(credentials: { email: string; password: string }): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/login`, credentials).pipe(
      tap(res => {
        if (res && res.token) {
          localStorage.setItem('token', res.token);
          // optionally save user
          localStorage.setItem('user', JSON.stringify(res.user || null));
        }
      })
    );
  }

  register(payload: { fullName: string; email: string; password: string }): Observable<any> {
    return this.http.post<any>(`${this.baseUrl}/register`, payload);
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('user');
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  isLoggedIn(): boolean {
    return !!this.getToken();
  }

  getCurrentUser(): any | null {
    const u = localStorage.getItem('user');
    return u ? JSON.parse(u) : null;
  }
}
