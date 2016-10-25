(function ($) {
    $(function () {

        $('.button-collapse').sideNav();

    }); // end of document ready
})(jQuery); // end of jQuery name space


$(document).ready(function () {
    $.getJSON("http://localhost:8080/municipio", function(dados){
        console.log(dados);
        $("#linhas").html("");
        
        var linhas = "";
        
        $.each(dados._embedded.municipios, function(i, obj) {
            console.log(obj.codigoIbge);
              linhas += "<tr>"
                     +"   <td>"+obj._links.self.href+"</td>"
                     +"   <td>"+obj.codigoIbge+"</td>"
                     +"   <td>"+obj.descricao+"</td>"
                     +"   <td>"+obj.uf+"</td>"
                     +"</tr>";
         });
         console.log(linhas);
         $("#linhas").append(linhas);
    });
});
