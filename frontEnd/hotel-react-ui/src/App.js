import { BrowserRouter, Route, Routes } from 'react-router-dom';
import Navbar from './component/common/Navbar';
import Footer from './component/common/Footer';
import Register from './component/auth/Register';


function App() {
  return (
    <BrowserRouter>

      <div className='App'>
        <Navbar />
      </div>

      <div className='content'>
        <Routes>
          <Route path='/register' element={<Register />} />
        </Routes>
      </div>

      <Footer />

    </BrowserRouter>
  );
}

export default App;
