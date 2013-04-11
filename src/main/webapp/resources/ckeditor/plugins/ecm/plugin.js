CKEDITOR.plugins.add( 'ecm',
{
	init: function( editor )
	{
		editor.ui.add('ecmOpts', CKEDITOR.UI_MENUBUTTON, {
			type : 'select',
			id : 'sport',
			label : 'Select your favourite sport',
			items : [ [ 'Basketball' ], [ 'Baseball' ], [ 'Hockey' ], [ 'Football' ] ],
			'default' : 'Football',
			onChange : function( api ) {
				// this = CKEDITOR.ui.dialog.select
				alert( 'Current value: ' + this.getValue() );
			}
		});
		
	}
} );