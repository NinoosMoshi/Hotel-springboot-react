import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Navbar from './component/common/Navbar';
import Footer from './component/common/Footer';
import Register from './component/auth/Register';
import Login from './component/auth/Login';


function App() {
  return (
    <BrowserRouter>

      <div className='App'>
        <Navbar />
      </div>

      <div className='content'>
        <Routes>
          <Route path='/register' element={<Register />} />
          <Route path='/login' element={<Login />} />
        </Routes>
      </div>

      <Footer />

    </BrowserRouter>
  );
}

export default App;
