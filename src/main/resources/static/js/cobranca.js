$('#confirmacaoExclusao').on('show.bs.modal', function(event) {
	
	var botaoExclusao = $(event.relatedTarget);
	
	var codigo = botaoExclusao.data('codigo');
	var descricao = botaoExclusao.data('descricao');
	
	var modal = $(this);
	
	var form = modal.find('form');
	var action = form.data('url-base');
	
	if(!action.endsWith('/')) {
		action += '/';
	}
	
	form.attr('action', action + codigo);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir o título <strong>'+descricao+'</strong>?');
});

$(function(){
	$('[rel="tooltip"]').tooltip();
	
	$('.js-currency').maskMoney({
		decimal: ',',
		thousands: '.',
		allowZero: true
	});
	
	$('.js-atualizar-status').on('click', function(event) {
		event.preventDefault();
		
		var botaoReceber = $(event.currentTarget);
		var urlReceber = botaoReceber.attr('href');
	});
});