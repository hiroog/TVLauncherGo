// 2019/02/03 Hiroyuki Ogasawara
// vim:ts=4 sw=4 noet:

package jp.flatlib.android.tvlaunchergo;


import	android.content.Context;
import	android.content.Intent;
import	android.content.pm.PackageManager;
import	android.graphics.drawable.Drawable;
import android.net.Uri;
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
			intent.addCategory( Intent.CATEGORY_LAUNCHER );
			mContext.startActivity( intent );
		}
	}

	@Override
	public boolean	onLongClick( View v )
	{
		Intent	intent= new Intent();
		intent.setAction( Settings.ACTION_APPLICATION_DETAILS_SETTINGS );
		intent.addCategory( Intent.CATEGORY_DEFAULT );
		intent.setData( Uri.parse( "package:" + Info.PackageName ) );
		mContext.startActivity( intent );
		return	true;
	}
}

