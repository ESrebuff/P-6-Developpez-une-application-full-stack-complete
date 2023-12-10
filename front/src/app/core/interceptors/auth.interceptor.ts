import { HttpEvent, HttpHandler, HttpHeaders, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Injectable } from "@angular/core";
import { EMPTY, Observable } from "rxjs";
import { AuthService } from '../services/auth.service';
import { Router } from "@angular/router";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

    constructor(private auth: AuthService) { }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const token = this.auth.getToken()
        if (token) {
            if (this.auth.isTokenExpired()) {
                this.auth.logout();
                return EMPTY;
            }
            const headers = new HttpHeaders()
                .append('Authorization', `Bearer ${token}`);
            const modifieReq = req.clone({ headers });
            return next.handle(modifieReq);
        }
        return next.handle(req);
    }
}