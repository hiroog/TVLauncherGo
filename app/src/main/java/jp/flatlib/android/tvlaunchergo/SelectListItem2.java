// 2019/02/03 Hiroyuki Ogasawara
// vim:ts=4 sw=4 noet:

package jp.flatlib.android.tvlaunchergo;


import android.app.ActivityOptions;
import	android.content.Context;
import	android.content.Intent;
import	android.content.pm.PackageManager;
import android.graphics.Rect;
import	android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import	android.view.View;


class SelectListItem2 implements View.OnClickListener, View.OnLongClickListener {

	private FileInfo	Info;
	private Context		mContext;

	//-------------------------------------------------------------------------
	//-------------------------------------------------------------------------

	public SelectListItem2( FileInfo info, Context context )
	{
		Info= info;
		mContext= context;
	}
	public String	getTitle()
	{
		return	Info.AppName;
	}
	public Drawable getImage()
	{
		return	Info.AppIcon;
	}
	public FileInfo	getFileInfo()
	{
		return	Info;
	}

	//-------------------------------------------------------------------------
	//-------------------------------------------------------------------------

	@Override
	public void	onClick( View v )
	{
		PackageManager pm= mContext.getPackageManager();
		Intent intent= pm.getLaunchIntentForPackage( Info.PackageName );
		if( intent != null ){

			Rect rect= new Rect( 0, 0, 1280, 720 );
			//Rect	rect= new Rect( 0, 0, 800, 450 );
			Bundle bundle= ActivityOptions.makeBasic().setLaunchBounds( rect ).toBundle();

			intent.addCategory( Intent.CATEGORY_LAUNCHER );
			//intent.addFlags( Intent.FLAG_ACTIVITY_SINGLE_TOP );
			intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK );
			mContext.startActivity( intent, bundle );
		}
	}

	@Override
	public boolean	onLongClick( View v )
	{
		Intent	intent= new Intent();
		intent.setAction( Settings.ACTION_APPLICATION_DETAILS_SETTINGS );
		intent.addCategory( Intent.CATEGORY_DEFAULT );
		intent.setData( Uri.parse( "package:" + Info.PackageName ) );
		intent.addFlags( Intent.FLAG_ACTIVITY_SINGLE_TOP );
		//intent.addFlags( Intent.FLAG_ACTIVITY_NEW_TASK );
		mContext.startActivity( intent );
		return	true;
	}
}

