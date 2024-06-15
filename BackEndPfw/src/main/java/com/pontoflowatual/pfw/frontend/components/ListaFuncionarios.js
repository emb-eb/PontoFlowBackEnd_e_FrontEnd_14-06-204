import React from 'react';
import Funcionario from './Funcionario';

const ListaFuncionarios = ({ funcionarios }) => {
  return (
    <div className="lista-funcionarios">
      <h2>Funcionários</h2>
      {funcionarios.map(funcionario => (
        <Funcionario key={funcionario.id} funcionario={funcionario} />
      ))}
    </div>
  );
}

export default ListaFuncionarios;