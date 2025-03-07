import axios from 'axios';
import CryptoJS from 'crypto-js';
import { use } from 'react';
import { de } from 'react-day-picker/locale';

export default class ApiService {

  static BASE_URL = 'http://localhost:9090/api';
  static ENCRYPT_KEY = '1234567890123456';

  // encrypt token using crypto-js
  static encrypt(token) {
    return CryptoJS.AES.encrypt(token, this.ENCRYPT_KEY.toString());
  }

  // decrypt token using crypto-js
  static decrypt(encryptedToken) {
    const bytes = CryptoJS.AES.decrypt(encryptedToken, this.ENCRYPT_KEY);
    return bytes.toString(CryptoJS.enc.Utf8);
  }

  // save token to local storage
  static saveToken(token) {
    const encryptedToken = this.encrypt(token);
    localStorage.setItem('token', encryptedToken);
  }

  // retrieve token from local storage
  static getToken() {
    const encryptedToken = localStorage.getItem('token');
    if (!encryptedToken) return null;
    return this.decrypt(encryptedToken);
  }

  // save role to Local Storage
  static saveRole(role) {
    const encryptedRole = this.encrypt(role);
    localStorage.setItem('role', encryptedRole);
  }

  // get role from Local Storage
  static getRole() {
    const encryptedRole = localStorage.getItem('role');
    if (!encryptedRole) return null;
    return this.decrypt(encryptedRole);
  }
  

  // remove token and role from local storage
  static clearAuth() {
    localStorage.removeItem('token');
    localStorage.removeItem('role');
  }

  
  static getHeader() {
    const token = this.getToken();
    return {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json' 
    }
    }


    /** AUTH AND USERS API METHODS **/
  

    // AUTH
    static async registerUser(registerationData) {
      const resp = await axios.post(`${this.BASE_URL}/auth/register`, registerationData);
      return resp.data;
    }

    static async loginUser(loginData) {
      const resp = await axios.post(`${this.BASE_URL}/auth/login`, loginData);
      return resp.data;
    }


    // USERS
    static async myProfile() {
      const resp = await axios.get(`${this.BASE_URL}/users/account`, {
        headers : this.getHeader()
      });
      return resp.data;
    }


    static async myBookings() {
      const resp = await axios.get(`${this.BASE_URL}/users/bookings`, {
        headers : this.getHeader()
      })
      return resp.data;
    }

    static async deleteAccount() {
      const resp = await axios.delete(`${this.BASE_URL}/users/delete`, {
        headers : this.getHeader()
      })
      return resp.data;
    }


    // ROOMS
    static async addRoom(formData) {
      const resp = await axios.post(`${this.BASE_URL}/rooms/add`, formData, {
        headers : {
          ...this.getHeader(),   // we used spread operator to make a copy of header into new object then we can ovveride the content type
          'Content-Type': 'multipart/form-data'
          // or we can use
          // const headers = this.getHeader();
          // headers['Content-Type'] = 'multipart/form-data';
        }
      });
      return resp.data;
    }



}