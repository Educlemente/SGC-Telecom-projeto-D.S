import React, { useState } from 'react';
import api from './api';

function App() {
  const [login, setLogin] = useState('');
  const [senha, setSenha] = useState('');

  const handleLogin = async (e) => {
    e.preventDefault();
    try {
      const resposta = await api.post('/auth/login', { login, senha });
      const token = resposta.data;
      
      // Salva o crachá VIP no navegador
      localStorage.setItem('token', token);
      
      alert('Login realizado com sucesso! Token salvo.');
      console.log('Token recebido:', token);
    } catch (error) {
      alert('Erro ao fazer login. Verifique se o Java está ligado e as credenciais estão corretas.');
    }
  };

  return (
    <div className="min-h-screen bg-slate-900 flex items-center justify-center p-4">
      <div className="bg-white p-8 rounded-2xl shadow-2xl w-full max-w-md">
        <h2 className="text-3xl font-extrabold mb-2 text-center text-blue-600">SGC Telecom</h2>
        <p className="text-gray-500 text-center mb-8">Área Restrita</p>
        
        <form onSubmit={handleLogin}>
          <div className="mb-4">
            <label className="block text-gray-700 text-sm font-semibold mb-2">E-mail</label>
            <input 
              className="w-full px-4 py-3 border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-blue-500" 
              type="email" 
              value={login}
              onChange={(e) => setLogin(e.target.value)}
              placeholder="admin@sgc.com.br" 
            />
          </div>
          
          <div className="mb-6">
            <label className="block text-gray-700 text-sm font-semibold mb-2">Senha</label>
            <input 
              className="w-full px-4 py-3 border border-gray-300 rounded-xl focus:outline-none focus:ring-2 focus:ring-blue-500" 
              type="password" 
              value={senha}
              onChange={(e) => setSenha(e.target.value)}
              placeholder="••••••••" 
            />
          </div>
          
          <button type="submit" className="w-full bg-blue-600 hover:bg-blue-700 text-white font-bold py-3 rounded-xl shadow-lg transition-all">
            Acessar Sistema
          </button>
        </form>
      </div>
    </div>
  );
}

export default App;