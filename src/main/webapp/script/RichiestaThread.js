//il tipo del thread inizialmente non è selezionato
document.getElementById("thread_type").selectedIndex = -1;


$("#richiestaT").on('click', function validate(){
    var thread_title  = $('#thread_title').val();
    var thread_type = $('#thread_type').val();
    var thread_text = $('#thread_text').val();
    var gameId = $('#gameID').val();
    var username = $('#username').val();

    //alert('pulsante premuto' + '1'+thread_title +'2'+ thread_type +'3'+ thread_text+'4'+ gameId +'5'+ username);

    if(thread_title === "")
    {
        Swal.fire({
            title: 'Inserire il titolo della discussione',
            type: 'warning',
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'OK',
            width: '400px',
        })
        setTimeout(function(){location.href="nuova-discussione.jsp"} , 135000);
        return false;
    }
    else
    {
        $.ajax({
            type: "POST",
            url: "CreateThreadReq",
            data: {"thread_title": thread_title, "thread_type": thread_type,"username":username, "thread_text": thread_text,"gameId":gameId},
            success: function(results){
                Swal.fire({ //SECONDO POPUP
                    title: 'Richiesta inviata!',
                    text: 'Essa sarà valutata da un moderatore',
                    timer: 40000,
                    type: 'success',
                    showCancelButton: false,
                    showConfirmButton: false,
                    width: '400px',
                })
                setTimeout(function(){location.href="http://localhost:8080/GamingZone_war_exploded/"} , 2000);
            }
        })
    }

});