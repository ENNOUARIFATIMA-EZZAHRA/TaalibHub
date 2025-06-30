import { HttpInterceptorFn } from '@angular/common/http';

export const JwtInterceptor: HttpInterceptorFn = (req, next) => {
  const token = localStorage.getItem('jwt_token') || localStorage.getItem('token');
  console.log('Interceptor - Token:', token);

  if (req.url.includes('/api/auth/')) {
    console.log(' Interceptor - Skipping auth request');
    return next(req);
  }

  if (token) {
    const cloned = req.clone({
      headers: req.headers.set('Authorization', `Bearer ${token}`)
    });
    console.log(' Interceptor - Adding Authorization header');
    return next(cloned);
  }

  console.log('Interceptor - No token found');
  return next(req);
};
