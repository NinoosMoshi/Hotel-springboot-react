import React from 'react';
import { useLocation, Navigate } from 'react-router-dom'; //useLocation : fetches the current URL path 
import ApiService from './ApiService';


export const CustomerRouter = ({element:Component}) => {
    const location = useLocation();
    
    return ApiService.isAuthenticated() ? Component
    : <Navigate to="/login" state={{ from: location }} replace />
}



export const AdminRouter = ({element:Component}) => {
    const location = useLocation();
   
    return ApiService.isAdmin() ? Component 
    : <Navigate to="/login" state={{ from: location }} replace />
}