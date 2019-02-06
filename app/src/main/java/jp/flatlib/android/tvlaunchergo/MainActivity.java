// 2019/02/03 Hiroyuki Ogasawara
// vim:ts=4 sw=4 noet:

package jp.flatlib.android.tvlaunchergo;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class MainActivity extends AppCompatActivity {

	private FileAdapter		mFileList= null;


	//-------------------------------------------------------------------------

	@Override
	protected void onCreate( Bundle savedInstanceState )
	{
		super.onCreate( savedInstanceState );
		setContentView( R.layout.activity_main );
		Toolbar toolbar= findViewById( R.id.toolbar );
		setSupportActionBar( toolbar );

/*
		FloatingActionButton	fab= findViewById( R.id.fab );
		fab.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
						.setAction("Action", null).show();
			}
		});
*/

		mFileList= new FileAdapter( this );
		GridView grid_view= findViewById( R.id.grid_list );
		grid_view.setAdapter( mFileList );
	}

	@Override
	public boolean	onCreateOptionsMenu( Menu menu )
	{
		getMenuInflater().inflate( R.menu.menu_main, menu );
		return	true;
	}

	@Override
	public boolean onOptionsItemSelected( MenuItem item )
	{
		int	id= item.getItemId();
		if( id == R.id.action_settings ){
			startActivity( new Intent( Settings.ACTION_SETTINGS ) );
			return	true;
		}
		return	super.onOptionsItemSelected(item);
	}

	@Override
	public void	onStart()
	{
		super.onStart();
		refreshMenu();
	}

	//-------------------------------------------------------------------------

	private void	refreshMenu()
	{
		PackageManager pm= getPackageManager();
		{
			Intent intent= new Intent();
			intent.addCategory( Intent.CATEGORY_LAUNCHER );
			intent.setAction( Intent.ACTION_MAIN );
			List<ResolveInfo> package_list= pm.queryIntentActivities( intent, 0 );
			mFileList.clear();
			for( ResolveInfo pack : package_list ){
				String	package_name= pack.activityInfo.packageName;
				if( package_name == null ){
					continue;
				}
				String		name= pack.loadLabel( pm ).toString();
				Drawable 	icon= pack.loadIcon( pm );
				//Log.i( "launcher2", "app=" + name + ", pack=" + pack.activityInfo.packageName );
				FileInfo	file_info= new FileInfo();
				file_info.AppName= name;
				file_info.AppIcon= icon;
				file_info.PackageName= package_name;
				mFileList.add( file_info, this );
            }
		}
		mFileList.notifyDataSetChanged();
	}


	//-------------------------------------------------------------------------


	class FileAdapter extends InfoMenuAdapter<SelectListItem2> {

		public FileAdapter( Context context )
		{
			super( context );
		}

		public void	add( FileInfo info, Context context )
		{
			add( new SelectListItem2( info, context ) );
		}

		@Override
		public View	getView( int position, View convertView, ViewGroup parent )
		{
			if( convertView == null ){
				convertView= Inflater.inflate( R.layout.grid_list, null );
			}
			SelectListItem2	item= get( position );
			TextView	view_image_name= (TextView)convertView.findViewById( R.id.image_name );
			ImageView	view_image= (ImageView)convertView.findViewById( R.id.list_image );
			if( view_image == null ){
				convertView= Inflater.inflate( R.layout.grid_list, null );
				view_image_name= (TextView)convertView.findViewById( R.id.image_name );
				view_image= (ImageView)convertView.findViewById( R.id.list_image );
			}

			view_image_name.setText( item.getTitle() );
			view_image.setImageDrawable( item.getImage() );
			view_image.setOnClickListener( item );
			view_image.setOnLongClickListener( item );

			return	convertView;
		}

	}
}
