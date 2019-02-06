// 2019/02/03 Hiroyuki Ogasawara
// vim:ts=4 sw=4 noet:

package jp.flatlib.android.tvlaunchergo;

import	android.view.View;
import	android.view.LayoutInflater;
import	android.view.ViewGroup;
import	android.widget.BaseAdapter;
import	android.content.Context;
import	java.util.ArrayList;



public class InfoMenuAdapter<T> extends BaseAdapter {

	protected LayoutInflater	Inflater;
	protected ArrayList<T>		TitleList;
	protected int				InfoPosition;

	@Override
	public int	getCount()
	{
		return	TitleList.size();
	}

	@Override
	public Object	getItem( int position )
	{
		return	TitleList.get( position );
	}

	public T	get( int position )
	{
		return	TitleList.get( position );
	}

	public void	add( T title )
	{
		TitleList.add( title );
	}

	public void	init_menu()
	{
	}

	public void	clear()
	{
		TitleList.clear();
		init_menu();
		InfoPosition= TitleList.size();
	}

	@Override
	public long		getItemId( int position )
	{
		return	position;
	}

	@Override
	public boolean	isEnabled( int position )
	{
		return	position < InfoPosition;
	}

	public boolean	isButton( int position )
	{
		return	position < InfoPosition;
	}

	public InfoMenuAdapter( Context context )
	{
		super();
		TitleList= new ArrayList<T>();
		clear();
		Inflater= (LayoutInflater)context.getSystemService( Context.LAYOUT_INFLATER_SERVICE );
	}

	@Override
	public View	getView( int position, View convertView, ViewGroup parent )
	{
		return	convertView;
	}
}



