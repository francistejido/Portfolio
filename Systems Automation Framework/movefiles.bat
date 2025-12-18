move a\b\F25_F10_franciscotejido_folder_b.txt a 
move a\c\F25_F10_franciscotejido_folder_c.txt a 
move a\b\d\F25_F10_franciscotejido_folder_d.txt a 
move a\b\e\F25_F10_franciscotejido_folder_e.txt a 
move a\c\f\F25_F10_franciscotejido_folder_f.txt a 
move a\c\g\F25_F10_franciscotejido_folder_g.txt a 
rd /s /q a\b a\c a\b\d a\b\e a\c\f a\c\g 
cd a 
ren F25_F10_franciscotejido_folder_a.txt January31.day 
ren F25_F10_franciscotejido_folder_b.txt February28.day 
ren F25_F10_franciscotejido_folder_c.txt March31.day 
ren F25_F10_franciscotejido_folder_d.txt April30.day 
ren F25_F10_franciscotejido_folder_e.txt May31.day 
ren F25_F10_franciscotejido_folder_f.txt June30.day 
ren F25_F10_franciscotejido_folder_g.txt July31.day 
cd ..
tree /f 
