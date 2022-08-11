/**
 * Questa funzione JQuery, associata al click di ciascun button corrispondente a una richiesta thread, si occupa di
 * invocare, a seguito di una conferma, la servlet RefuseRequest per richiedere l'operazione di rifiuto della richiesta.
 */
$("#request .refuseButton").each(function () {
    $(this).on("click", function refuseRequest() {
    	var reqid = this.value;
        //alert(val);
        Swal.fire({ //PRIMO POPUP
			  title: 'Sei sicuro di voler rifiutare la richiesta?',
			  text: "La richiesta sarà eliminata dal sistema.",
			  type: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Conferma',
			  cancelButtonText: 'Annulla'
			}).then((result) => {
			  if (result.value) {
				  //alert(reqid + " " + action);
				 		$.ajax({ //INVOCAZIONE AJAX
					  	type: "GET",
					    url: "RefuseReq",
					    data: {"reqid": reqid},
					    success: function(results){
					    	Swal.fire({ //SECONDO POPUP
					  			  title: 'Richiesta Eliminata',
					  			  timer: 1000,
					  			  type: 'success',
					  			  showCancelButton: false,
					  			  showConfirmButton: false,
					  			  width: '400px',
					  			})
					  		setTimeout(function(){location.href="http://localhost:8080/GamingZone_war_exploded/richieste.jsp"} , 1000);
						  }
					})
			  	}
			})
    });
});


/**
 * Questa funzione JQuery, associata al click di ciascun button corrispondente a una richiesta thread, si occupa di
 * invocare, a seguito di una conferma, la servlet AcceptRequest per richiedere l'operazione di accettazione della richiesta.
 */
$("#request .acceptButton").each(function () {
    $(this).on("click", function acceptRequest() {
    	var reqid = this.value;
        //alert(val);
        Swal.fire({ //PRIMO POPUP
			  title: 'Sei sicuro di voler accettare la richiesta?',
			  text: "Il gioco richiesto sarà aggiunto al sistema.",
			  type: 'warning',
			  showCancelButton: true,
			  confirmButtonColor: '#3085d6',
			  cancelButtonColor: '#d33',
			  confirmButtonText: 'Conferma',
			  cancelButtonText: 'Annulla'
			}).then((result) => {
			  if (result.value) {
				 		$.ajax({ //INVOCAZIONE AJAX
					  	type: "GET",
					    url: "AcceptReq",
					    data: {"reqid": reqid},
					    success: function(results){
					    	Swal.fire({ //SECONDO POPUP
					  			  title: 'Richiesta Approvata',
					  			  text: "Il gioco è stato aggiunto!",
					  			  timer: 1000,
					  			  type: 'success',
					  			  showCancelButton: false,
					  			  showConfirmButton: false,
					  			  width: '400px',
					  			})
					  		setTimeout(function(){location.href="http://localhost:8080/GamingZone_war_exploded/richieste.jsp"} , 1000);
						  }
					})
			  	}
			})
    });
});
