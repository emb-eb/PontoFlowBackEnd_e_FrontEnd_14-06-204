// components/AdicionarFuncionario.js

import React, { useState } from 'react';

const AdicionarFuncionario = ({ onAdicionar }) => {
  const [nome, setNome] = useState('');
  const [departamento, setDepartamento] = useState('');
  const [idade, setIdade] = useState('');

  const handleSubmit = (e) => {
    e.preventDefault();
    onAdicionar({ nome, departamento, idade });
    setNome('');
    setDepartamento('');
    setIdade('');
  }

  return (
    <div className="adicionar-funcionario">
      <h2>Adicionar Funcionário</h2>
      <form onSubmit={handleSubmit}>
        <input type="text" placeholder="Nome" value={nome} onChange={(e) => setNome(e.target.value)} />
        <input type="text" placeholder="Departamento" value={departamento} onChange={(e) => setDepartamento(e.target.value)} />
        <input type="number" placeholder="Idade" value={idade} onChange={(e) => setIdade(e.target.value)} />
        <button type="submit">Adicionar</button>
      </form>
    </div>
  );
}

export default AdicionarFuncionario;
