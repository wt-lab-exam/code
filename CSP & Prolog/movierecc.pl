% Facts representing movies and their genres
movie(iron_man, action).
movie(the_shawshank_redemption, drama).
movie(inception, sci-fi).
movie(the_dark_knight, action).
movie(pulp_fiction, crime).
movie(frozen, animation).
movie(coco, animation).
movie(black_panther, action).
movie(finding_nemo, animation).
movie(forrest_gump, drama).
 
% User preferences
user_likes(john, action).
user_likes(alice, animation).
user_likes(bob, drama).
 
% Predicate to track watched movies
watched(john, pulp_fiction).
watched(john, finding_nemo).
watched(alice, frozen).
watched(bob, the_dark_knight).
watched(bob, forrest_gump).
 
% AI predicate for movie recommendation
recommend_movie(User,Movie) :-
    user_likes(User, Genre),
    movie(Movie, Genre),
    \+ watched(User, Movie).