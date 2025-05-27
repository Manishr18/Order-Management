
import './App.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import  Home  from './pages/home';
import  History  from './pages/History';
import Stock from './pages/Stock';
import { Orderform } from './components/orderform';

function App() {
  return (
   <Router>
    <Routes>
      <Route path="/" element={<Home/>}></Route>
      <Route path="/History" element={<History/>}></Route>
      <Route path="/Stock" element={<Stock/>}></Route>
      <Route path="/Orderform" element={<Orderform/>}></Route>
    </Routes>
   </Router>
  );
}

export default App;
