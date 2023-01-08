package com.tyss.baseutil;

public interface EndPoints {
	long max_time=3000;
	int created=201;
	int ok=200;
	
	String get_album="/albums/";
	String get_several_album="/albums";
	String get_user_saved_album="/me/albums";
	String save_album_for_current_user="/me/albums";
	String remove_user_saved_albums="/me/albums";
	String check_user_saved_albums="/me/albums/contains";
	String get_new_releases_albums="/browse/new-releases";
	
	String get_artists="/artists/";
	String get_several_artists="/artists";
	String get_artist_albums="/artists/{id}/albums";
	String get_artist_top_track="/artists/{id}/top-tracks";
	String get_artist_related_artists="/artists/{id}/related-artists";
	
	String get_playlist="/playlists/{playlist_id}";
	String create_playlist="/users/{user_id}/playlists";
	String change_playlist_details="/playlists/{playlist_id}";
	String get_playlist_items="/playlists/{playlist_id}/tracks";
	String add_items_playlist="/playlists/{playlist_id}/tracks";
	String get_user_current_playlist="/me/playlists";
	String get_users_playlist="/users/{user_id}/playlists";
	String get_featured_playlist="/browse/featured-playlists";
	String get_category_playlist="/browse/categories/{category_id}/playlists";
	
	String get_track="/tracks/{id}";
	String get_several_tracks="/tracks";
	String get_user_saved_tracks="/me/tracks";
	String save_track_for_current_user="/me/tracks";
	String remove_user_saved_tracks="/me/tracks";
	String check_user_saved_tracks="/me/tracks/contains";
	String get_track_audio_features="/audio-features";
	
	
	String search_for_items="/search";
	String get_available_market="/markets";
	String get_several_browse_categories="/browse/categories";
	String get_single_browse_category="/browse/categories/{category_id}";
	
	
}
