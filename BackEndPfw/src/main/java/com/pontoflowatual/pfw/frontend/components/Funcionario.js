import React from 'react';

const Funcionario = ({ funcionario }) => {
  return (
    <div className="funcionario">
      <h3>{funcionario.nomefuncionario}</h3>
      <p>Departamento: {funcionario.departamento}</p>
      <p>Idade: {funcionario.idade}</p>
    </div>
  );
}

export default Funcionario;