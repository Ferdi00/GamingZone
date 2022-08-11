/**
 * Questa funzione JQuery, associata al click del button, prende i parametri dal form di aggiunta del gioco e 
 * verifica se hanno un formato corretto, invocando l'operazione della servlet in caso affermativo.
 */
$("#buttonaggiungi").on('click', function validate(){	
		var game_title  = $('#game_title').val();
		var publisher  = $('#publisher').val();
		var score = $('#score').val();
		var game_genre = $('#game_genre').val();
		var game_year = $('#game_year').val();
		var game_desc = $('#game_desc').val();
		
		//alert('pulsante premuto' + '1:'+game_title +' 2:'+ publisher +' 3:'+ game_genre+' 4:'+ game_year +' 5:'+ game_desc + ' 6: '+ score);
		
		if(game_title === "")
		{
			Swal.fire({ 
				title: 'Inserire il titolo del gioco',
				type: 'warning',
				  confirmButtonColor: '#3085d6',
				  confirmButtonText: 'OK',
				width: '400px',
				})
			setTimeout(function(){location.href="nuovo-gioco.jsp"} , 135000);
			return false;
		} else if(publisher === "")
		{
			Swal.fire({ 
				title: 'Inserire un publisher',
				type: 'warning',
				  confirmButtonColor: '#3085d6',
				  confirmButtonText: 'OK',
				width: '400px',
				})
			setTimeout(function(){location.href="nuovo-gioco.jsp"} , 135000);
			return false;
		}
		else 
		{
			$.ajax({ 
			type: "POST",
			url: "AddGame",
			data: {"game_title": game_title, "publisher": publisher,"score":score, "game_genre":game_genre, "game_year":game_year, "game_desc": game_desc},
			success: function(results){
				Swal.fire({ //SECONDO POPUP
					title: 'Gioco aggiunto!',
					timer: 40000,
					type: 'success',
					showCancelButton: false,
					showConfirmButton: false,
					width: '400px',
					})
				setTimeout(function(){location.href="FindAll"} , 2000);
				},
				error: function (result){
					Swal.fire({ 
						title: 'Il gioco e gia presente!',
						type: 'error',
						showCancelButton: false,
						showConfirmButton: false,
						width: '400px',
						})
					setTimeout(function(){location.href="nuovo-gioco.jsp"} , 135000);
				}
			})
		}
		
		
		
	});
	
	