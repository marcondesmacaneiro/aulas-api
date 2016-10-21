# aulas-api-spring-boot

https://spring.io/guides/gs/consuming-rest-android/

http://square.github.io/retrofit/

---------------------------------------------------------------------

Primeira parte da Documentação

Listando todos os dados do CENSO
GET /censo

curl -X GET -H "Content-Type: application/json" -H "Cache-Control: no-cache" "http://localhost:8080/censo"

---------------------------------------------------------------------
Para gravar algum dado no CENSO. Será preciso duas Informações coletor, que é o identificar do "equipamento" de coleta. Dados, que será um JSON, assim ficando transparente o armazenamento para qualquer pessoa que implementar.

POST /censo

curl -X POST -H "Content-Type: application/json" -H "Cache-Control: no-cache" -d '{
	"coletor":"1000",
	"dados":"{\"nome\":\"Marcondes\",\"endereco\":\"Rua alguma coisa, 10\" }"
}' "http://localhost:8080/censo"


---------------------------------------------------------------------
Para localizar apenas um registro do CENSO

GET /censo/{id}

curl -X GET -H "Content-Type: application/json" -H "Cache-Control: no-cache" "http://localhost:8080/censo/{id}"

---------------------------------------------------------------------
Para listar todos os dados do CENSO de um determinado "equipamento"

GET /censo/search/findByColetor?coletor={id}

curl -X GET -H "Content-Type: application/json" -H "Cache-Control: no-cache" "http://localhost:8080/censo/search/findByColetor?coletor={id}"

---------------------------------------------------------------------
Para alterar as infomações de um registro já gravado

PATCH /censo/{id}

curl -X PATCH -H "Content-Type: application/json" -H "Cache-Control: no-cache" -d '{
	"coletor":"1000",
	"dados":"{\"nome\":\"Marcondes NOVO\",\"endereco\":\"Rua alguma coisa, 10\" }"
}' "http://localhost:8080/censo/{id}"