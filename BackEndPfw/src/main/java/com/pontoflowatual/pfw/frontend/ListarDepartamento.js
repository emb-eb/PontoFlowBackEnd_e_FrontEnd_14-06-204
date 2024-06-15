const url = "http://localhost:8082/api/departamentos";

function hideLoader() {
  document.getElementById("loading").style.display = "none";
}

function show(departamentos) {
  let tab = `
    <thead>
      
      <th scope="col">Nome</th>
      <th scope="col">Descrica</th>
      <th scope="col">Diregente</th>
    


      <th scope="col">ID /th> <!-- Alterado o cabeçalho da coluna -->
    </thead>
    <tbody>`;

  for (let departamentos of departamentos) { // Renomeado a variável para funcionario
    tab += `
      <tr>
        
        <td>${departamentos.nomedepartamento}</td> 
        <td>${departamentos.descricaoDepart}</td> 
        <td>${departamentos.funcionarios}</td> 
      
        <td>${departamentos.id}</td> 
      </tr>`;
  }

  tab += `</tbody>`;
  document.getElementById("departamentos").innerHTML = tab; // Alterado para "funcionarios" ao invés de "tasks"
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
