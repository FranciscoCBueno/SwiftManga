import { createBrowserRouter, Navigate } from 'react-router-dom';
import App from '../App'
import { Login } from '../components/Login/Login';
import { Home } from '../components/Home/Home';

export const Rotas = createBrowserRouter([
    {
        path:"/",
        element: <App/>,
        children: [
            {
                index: true,
                element: <Navigate to="login" replace/>
            },
            {
                path: "/login",
                element: <Login/>
            },
            {
                path: "/home",
                element: <Home/>
            }
        ]
    }
]);