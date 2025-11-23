import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from 'src/environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApiService {
  base = environment.apiBaseUrl;

  constructor(private http: HttpClient) {}

  get<T>(path: string, params?: any): Observable<T> {
    const url = `${this.base}/${path}`;
    return this.http.get<T>(url, { params });
  }

  post<T>(path: string, body: any): Observable<T> {
    const url = `${this.base}/${path}`;
    return this.http.post<T>(url, body);
  }

  put<T>(path: string, body: any): Observable<T> {
    return this.http.put<T>(`${this.base}/${path}`, body);
  }

  delete<T>(path: string): Observable<T> {
    return this.http.delete<T>(`${this.base}/${path}`);
  }
}
