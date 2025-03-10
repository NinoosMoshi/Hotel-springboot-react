import { useNavigate } from 'react-router-dom'
import React, { useState } from 'react'
import ApiService from "../../service/ApiService";

export default function Register() {

  const [formData, setFormData] = useState({
    firstName: '',
    lastName: '',
    phoneNumber: '',
    email: '',
    password: ''
  });

  const [message, setMessage] = useState({ type: "", text: "" });
  const navigate = useNavigate();

  // handle input change
  const handleInputChange = ({ target: { name, value } }) => {
    setFormData((prev) => ({ ...prev, [name]: value }));
  }

  // validate form field
  const isFormValid = Object.values(formData).every((field) => field.trim());

  // handle form submission
  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!isFormValid) {
      setMessage({ type: "error", text: "Please fill all fields" });
      setTimeout(() => setMessage({}), 5000);
      return;
    }

    try {
      const resp = await ApiService.registerUser(formData);
      if (resp.status === 200) {
        setMessage({ type: "success", text: "Registration successful" });
        setTimeout(() => navigate('/login'), 3000)
      }
    }
    catch (error) {
      setMessage({ type: "error", text: error.response?.data?.message || error.message });
      setTimeout(() => setMessage({}), 5000);
    }
  }

  return (
    <div className='auth-container'>
      {message.text && (<p className={`${message.type}-message`}>{message.text}</p>)}

      <h2>Register</h2>
      <form onSubmit={handleSubmit}>
        {["firstName", "lastName", "phoneNumber", "email", "password"].map(
          (field) => (
            <div className='form-group' key={field}>
              <label>{field.replace(/([A-Z])/g, ' $1').trim()}: </label>
              <input type={field === "email" ? "email" : "text"}
                name={field}
                value={formData[field]}
                onChange={handleInputChange}
                required
              />
            </div>
          )
        )}
        <button type='submit'>Register</button>
      </form>
      <p className='register-link'>Already have an account? <a href='/login'>Login</a></p>
    </div>
  )
}
