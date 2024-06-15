const url = "http://localhost:8082/api/funcionarios";

function hideLoader() {
  document.getElementById("loading").style.display = "none";
}

function show(funcionarios) {
  let tab = `
    <thead>
      
      <th scope="col">Nome</th>
      <th scope="col">Email</th>
      <th scope="col">Nº CNI </th>
      <th scope="col">Nº NIF</th>
      <th scope="col">Data Admicao</th>
      <th scope="col">Data Nascimento</th>
      <th scope="col">Funcao</th>
      <th scope="col">Departmaneto</th>


      <th scope="col">ID do Funcionário</th> <!-- Alterado o cabeçalho da coluna -->
    </thead>
    <tbody>`;

  for (let funcionario of funcionarios) { // Renomeado a variável para funcionario
    tab += `
      <tr>
        
        <td>${funcionario.nome_funcionario}</td> 
        <td>${funcionario.email}</td> 
        <td>${funcionario.numCni}
        <td>${funcionario.numNIF}
        <td>${funcionario.dataAdmissao}</td> 
        <td>${funcionario.dataNascimento}</td> 
        <td>${funcionario.funcaofunc}</td>        
        <td>${funcionario.departamenentoid}</td>  
        <td>${funcionario.id}</td> 
      </tr>`;
  }

  tab += `</tbody>`;
  document.getElementById("funcionarios").innerHTML = tab; // Alterado para "funcionarios" ao invés de "tasks"
}

async function getAPI() {
  try {
    const response = await fetch(url, { method: "GET" });

    if (!response.ok) {
      throw new Error(`Erro HTTP! Status: ${response.status}`);
    }

    const data = await response.json();
    console.log(data);

    hideLoader();
    show(data);
  } catch (error) {
    console.error("Erro ao buscar dados:", error.message);
    // Manipular o erro conforme necessário (por exemplo, exibir uma mensagem de erro para o usuário)
  }
}

getAPI();
