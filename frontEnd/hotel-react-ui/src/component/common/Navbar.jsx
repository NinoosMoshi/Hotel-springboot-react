import React from 'react';
import { useNavigate, NavLink } from 'react-router-dom';
import ApiService  from '../../service/ApiService';


export default function Navbar() {

  const isAuthinticated = ApiService.isAuthenticated();
  const isCustomer = ApiService.isCustomer();
  const isAdmin = ApiService.isAdmin();

    const navigate = useNavigate();

    const handleLogout = () => {
      ApiService.logout();
      navigate('/login');
    }

  return (
    <nav className='navbar'>
      <div className='navbar-brand'>
        <NavLink to='/home'> Ankedo Hotel </NavLink>
      </div>

      <ul className='navbar-ul'>
        <li><NavLink to={"/home"} activeClassName="active">Home</NavLink></li>
        <li><NavLink to={"/rooms"} activeClassName="active">Room</NavLink></li>
        <li><NavLink to={"/find-booking"} activeClassName="active">Find My Bookings</NavLink></li>

        { isCustomer && <li><NavLink to={"/profile"} activeClassName="active">Profile</NavLink></li>}
        { isAdmin && <li><NavLink to={"/admin"} activeClassName="active">Admin</NavLink></li>}

        { !isAuthinticated && <li><NavLink to={"/login"} activeClassName="active">Login</NavLink></li>}
        { !isAuthinticated && <li><NavLink to={"/register"} activeClassName="active">Register</NavLink></li>}

        { isAuthinticated && <li>onClick={handleLogout}Logout</li>}
      </ul>
    </nav>
  )
}
