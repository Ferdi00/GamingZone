/**
 * il punteggio del gioco ed eventualmente il punteggio e la categoria corrispondenti all'utente
 */
var score  = $('#score').text();
var usrScore = $('#usrScore').text();
var user_category = $('#userCategory').text();

/**
 * Questa funzione JQuery, associata al click del button di aggiunta alla lista, verifica prima se
 * la categoria della lista non è stata già selezionata dall'utente, in caso negativo procede all'invocazione della
 * servlet apposita: AddToList se l'utente non aveva già aggiunto quel gioco alla sua lista o ChangeCategory se
 * aveva già aggiunto il gioco ma con una diversa categoria.
 */
$("#addButton").on("click", function addToList() {
	var category_value = $('#category').val();
    //alert(username+" ha aggiunto alla lista di categoria "+category_value+" il gioco id "+game_id+" con un punteggio di "+score+" da un punteggio di "+usrScore);
    //alert(category_value + " " + user_category);
    if(category_value === user_category){
    //alert("categorie uguali");
    
  	Swal.fire({ //SECONDO POPUP
		  			  title: 'Categoria gia selezionata!',
		  			  timer: 1000,
		  			  type: 'warning',
		  			  showCancelButton: false,
		  			  showConfirmButton: false,
		  			  width: '400px',
		  			})
		  			setTimeout(function(){location.href="GamePage?id="+game_id} , 1000);
    }
    else if(user_category === " "){
      $.ajax({ //INVOCAZIONE AJAX
		  	type: "GET",
		    url: "AddToList",
		    data: {"username" : username, "game_id": game_id, "category_value": category_value, "score": score, "usrScore": usrScore},
		    success: function(results){
		    	Swal.fire({ //SECONDO POPUP
		  			  title: 'Aggiunto alla lista!',
		  			  timer: 1000,
		  			  type: 'success',
		  			  showCancelButton: false,
		  			  showConfirmButton: false,
		  			  width: '400px',
		  			})
		  			setTimeout(function(){location.href="GamePage?id="+game_id} , 1000);
			  }
		})
    }
    else {
      $.ajax({ //INVOCAZIONE AJAX
		  	type: "GET",
		    url: "ChangeCateg",
		    data: {"username" : username, "game_id": game_id, "category_value": category_value, "score": score, "usrScore": usrScore, "user_category": user_category},
		    success: function(results){
		    	Swal.fire({ //SECONDO POPUP
		  			  title: 'Categoria aggiornta!',
		  			  timer: 1000,
		  			  type: 'success',
		  			  showCancelButton: false,
		  			  showConfirmButton: false,
		  			  width: '400px',
		  			})
		  			setTimeout(function(){location.href="GamePage?id="+game_id} , 1000);
			  }
		})
    }

});

/**
 * Questa funzione JQuery, associata al click del button di rimozione dalla lista (se esistente), si occupa di invocare
 * la servlet DeleteFromList per richiedere l'operazione di rimozione.
 */
    $('#deleteFromList').on("click", function deleteFromList() {
    	//alert(username + " vuole togliere dalla lista " +game_id+" che ha salvato come " +user_category+ " punteggio: "+usrScore +" "+score);
    				$.ajax({ //INVOCAZIONE AJAX
					  	type: "GET",
					    url: "DeleteFromList",
					    data: {"username" : username, "game_id": game_id, "score": score, "usrScore": usrScore, "user_category": user_category},
					    success: function(results){
					    	Swal.fire({ //SECONDO POPUP
					  			  title: 'Gioco rimosso dalla lista',
					  			  timer: 1000,
					  			  type: 'success',
					  			  showCancelButton: false,
					  			  showConfirmButton: false,
					  			  width: '400px',
					  			})
					  		setTimeout(function(){location.href="GamePage?id="+game_id} , 1000);
						  }
					})
			});
