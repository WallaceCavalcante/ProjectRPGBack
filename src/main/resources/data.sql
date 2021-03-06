INSERT INTO WEAPON(name, attack, rarity, level) VALUES('Basic Sword', 11.0, 'Common', 1);       --1
INSERT INTO WEAPON(name, attack, rarity, level) VALUES('Basic Axe', 12.0, 'Common', 1);         --2
INSERT INTO WEAPON(name, attack, rarity, level) VALUES('Basic Dagger', 8.0, 'Common', 1);       --3
INSERT INTO WEAPON(name, attack, rarity, level) VALUES('Basic Bow', 10.0, 'Common', 1);         --4
INSERT INTO WEAPON(name, attack, rarity, level) VALUES('Basic Staff', 11.0, 'Common', 1);       --5
INSERT INTO WEAPON(name, attack, rarity, level) VALUES('Giant Axe', 49.0, 'Uncommon', 15);      --6
INSERT INTO WEAPON(name, attack, rarity, level) VALUES('Destructor', 97.0, 'Legendary', 30);    --7
INSERT INTO WEAPON(name, attack, rarity, level) VALUES('Excalibur', 9999999.0, 'Undefined', 99);--8
INSERT INTO WEAPON(name, attack, rarity, level) VALUES('Basic Hammer', 18.0, 'Common', 1);      --9
INSERT INTO WEAPON(name, attack, rarity, level) VALUES('Hammer of God', 170.0, 'Legendary', 50);--10
INSERT INTO WEAPON(name, attack, rarity, level) VALUES('Orc Hammer', 23.0, 'Uncommon', 10);     --11
INSERT INTO WEAPON(name, attack, rarity, level) VALUES('Orc Sword', 18.0, 'Uncommon', 17);      --12
INSERT INTO WEAPON(name, attack, rarity, level) VALUES('Orc Bow', 19.5, 'Uncommon', 18);        --13
INSERT INTO WEAPON(name, attack, rarity, level) VALUES('Goblin Dagger', 22.0, 'Uncommon', 17);  --14
INSERT INTO WEAPON(name, attack, rarity, level) VALUES('Goblin Sword', 27.0, 'Uncommon', 19);   --15
INSERT INTO WEAPON(name, attack, rarity, level) VALUES('Goblin Bow', 30.0, 'Uncommon', 18);     --16
INSERT INTO WEAPON(name, attack, rarity, level) VALUES('Goblin Staff', 33.0, 'Uncommon', 20);   --17
INSERT INTO WEAPON(name, attack, rarity, level) VALUES('Supreme Hammer', 62.0, 'Uncommon', 25); --18
INSERT INTO WEAPON(name, attack, rarity, level) VALUES('Goblin Knife', 18.0, 'Uncommon', 14);   --19
INSERT INTO WEAPON(name, attack, rarity, level) VALUES('Basic Pistols', 5.7, 'Common', 1);      --20
INSERT INTO WEAPON(name, attack, rarity, level) VALUES('Intermediate Sword', 16.0, 'Common', 1);--21
INSERT INTO WEAPON(name, attack, rarity, level) VALUES('Intermediate Axe', 17.0, 'Common', 1);  --22
INSERT INTO WEAPON(name, attack, rarity, level) VALUES('Intermediate Dagger', 13.0, 'Common', 1);--23
INSERT INTO WEAPON(name, attack, rarity, level) VALUES('Intermediate Bow', 15.0, 'Common', 1);  --24
INSERT INTO WEAPON(name, attack, rarity, level) VALUES('Intermediate Staff', 16.0, 'Common', 1);--25
INSERT INTO WEAPON(name, attack, rarity, level) VALUES('Intermediate Pistols', 10.7, 'Common', 1);--26
INSERT INTO WEAPON(name, attack, rarity, level) VALUES('Intermediate Hammer', 23.0, 'Common', 1);--27
INSERT INTO WEAPON(name, attack, rarity, level) VALUES('Advanced Sword', 21.0, 'Uncommon', 1);   --28
INSERT INTO WEAPON(name, attack, rarity, level) VALUES('Advanced Axe', 22.0, 'Uncommon', 1);     --29
INSERT INTO WEAPON(name, attack, rarity, level) VALUES('Advanced Dagger', 18.0, 'Uncommon', 1);  --30
INSERT INTO WEAPON(name, attack, rarity, level) VALUES('Advanced Bow', 20.0, 'Uncommon', 1);     --31
INSERT INTO WEAPON(name, attack, rarity, level) VALUES('Advanced Staff', 21.0, 'Uncommon', 1);   --32
INSERT INTO WEAPON(name, attack, rarity, level) VALUES('Advanced Pistols', 15.7, 'Uncommon', 1); --33
INSERT INTO WEAPON(name, attack, rarity, level) VALUES('Advanced Hammer', 28.0, 'Uncommon', 1);  --34
INSERT INTO WEAPON(name, attack, rarity, level) VALUES('Heroic Sword', 29.0, 'Rare', 1);         --35
INSERT INTO WEAPON(name, attack, rarity, level) VALUES('Heroic Axe', 30.0, 'Rare', 1);           --36
INSERT INTO WEAPON(name, attack, rarity, level) VALUES('Heroic Dagger', 26.0, 'Rare', 1);        --37
INSERT INTO WEAPON(name, attack, rarity, level) VALUES('Heroic Bow', 28.0, 'Rare', 1);           --38
INSERT INTO WEAPON(name, attack, rarity, level) VALUES('Heroic Staff', 29.0, 'Rare', 1);         --39
INSERT INTO WEAPON(name, attack, rarity, level) VALUES('Heroic Pistols', 23.7, 'Rare', 1);       --40
INSERT INTO WEAPON(name, attack, rarity, level) VALUES('Heroic Hammer', 36.0, 'Rare', 1);        --41

INSERT INTO ARMOR(name, defense, rarity, level) VALUES('No Armor', 1.0, '-', 0);                        --1
INSERT INTO ARMOR(name, defense, rarity, level) VALUES('Basic Armor', 4.0, 'Common', 1);                --2
INSERT INTO ARMOR(name, defense, rarity, level) VALUES('Medium Armor', 7.0, 'Common', 1);               --3
INSERT INTO ARMOR(name, defense, rarity, level) VALUES('Advanced Armor', 11.0, 'Common', 1);             --4
INSERT INTO ARMOR(name, defense, rarity, level) VALUES('Heavy Armor', 15.0, 'Uncommon', 15);             --5
INSERT INTO ARMOR(name, defense, rarity, level) VALUES('Fire Armor', 35.0, 'Rare', 30);                 --6
INSERT INTO ARMOR(name, defense, rarity, level) VALUES('Infinity Armor', 9999999.0, 'Undefined', 99);   --7
INSERT INTO ARMOR(name, defense, rarity, level) VALUES('Armor of God', 65.0, 'Legendary', 50);          --8
INSERT INTO ARMOR(name, defense, rarity, level) VALUES('Armor Just to Delete', 0.0, 'DELETE ME', 0);    --9

INSERT INTO MONSTER(name, level, weapon_id, armor_id, hp, race, xp_when_killed) VALUES('Tiny Orc', 2, 1, 1, 5, 'Orc', 2);               --1
INSERT INTO MONSTER(name, level, weapon_id, armor_id, hp, race, xp_when_killed) VALUES('Black Orc', 5, 2, 2, 10, 'Orc', 3);         --2
INSERT INTO MONSTER(name, level, weapon_id, armor_id, hp, race, xp_when_killed) VALUES('Red Orc', 7, 11, 3, 12, 'Orc', 4);          --3
INSERT INTO MONSTER(name, level, weapon_id, armor_id, hp, race, xp_when_killed) VALUES('Archer Orc', 8, 13, 2, 10, 'Orc', 4);       --4
INSERT INTO MONSTER(name, level, weapon_id, armor_id, hp, race, xp_when_killed) VALUES('Giant Orc', 10, 12, 4, 20, 'Orc', 7);       --5

INSERT INTO MONSTER(name, level, weapon_id, armor_id, hp, race, xp_when_killed) VALUES('Tiny Goblin', 14, 19, 3, 7, 'Goblin', 10);           --6
INSERT INTO MONSTER(name, level, weapon_id, armor_id, hp, race, xp_when_killed) VALUES('Assassin Goblin', 17, 14, 4, 7, 'Goblin', 12);  --7
INSERT INTO MONSTER(name, level, weapon_id, armor_id, hp, race, xp_when_killed) VALUES('Warrior Goblin', 19, 15, 5, 7, 'Goblin', 15);   --8
INSERT INTO MONSTER(name, level, weapon_id, armor_id, hp, race, xp_when_killed) VALUES('Archer Goblin', 18, 16, 4, 7, 'Goblin', 15);    --9
INSERT INTO MONSTER(name, level, weapon_id, armor_id, hp, race, xp_when_killed) VALUES('Mage Goblin', 23, 17, 4, 7, 'Goblin', 18);     --10
INSERT INTO MONSTER(name, level, weapon_id, armor_id, hp, race, xp_when_killed) VALUES('Supreme Goblin', 25, 18, 5, 7, 'Goblin', 25);  --11


INSERT INTO BOSS(name, level, weapon_id, armor_id, hp, race, xp_when_killed) VALUES ('Captain Orc', 15, 6, 5, 50, 'Orc', 150);      --1
INSERT INTO BOSS(name, level, weapon_id, armor_id, hp, race, xp_when_killed) VALUES ('Goblin King', 30, 7, 6, 1, 'Goblin', 300);   --2

INSERT INTO PLAYER(nickname, level, hp, classe, gender, weapon_id, armor_id, xp) VALUES('Wace', 1, 9, 'Assassino', 'Masculino', 3, 2, 0);          --1
INSERT INTO PLAYER(nickname, level, hp, classe, gender, weapon_id, armor_id, xp) VALUES('GM', 99, 99999999, 'Guerreiro', 'Feminino', 8, 7, 0);     --2

INSERT INTO QUEST(name, target, monster_id, completed) VALUES ('The start of your journey', 2, 1, false);
INSERT INTO QUEST(name, target, monster_id, completed) VALUES ('A little more hard', 2, 2, false);
INSERT INTO QUEST(name, target, monster_id, completed) VALUES ('Lets kill more strong orcs', 2, 3, false);
INSERT INTO QUEST(name, target, monster_id, completed) VALUES ('Archer Orcs?!?!?!', 2, 4, false);
INSERT INTO QUEST(name, target, monster_id, completed) VALUES ('They are so strong!', 2, 5, false);
INSERT INTO QUEST(name, target, boss_id, completed) VALUES ('Your first real challenge', 1, 1, false);

INSERT INTO QUEST(name, target, monster_id, completed) VALUES ('They are so tiny but so fast too', 15, 6, false);
INSERT INTO QUEST(name, target, monster_id, completed) VALUES ('More fast and more hard', 15, 7, false);
INSERT INTO QUEST(name, target, monster_id, completed) VALUES ('Too many goblins', 20, 8, false);
INSERT INTO QUEST(name, target, monster_id, completed) VALUES ('Too many goblins 2', 20, 9, false);
INSERT INTO QUEST(name, target, monster_id, completed) VALUES ('Goblins know magic? Really?', 15, 10, false);
INSERT INTO QUEST(name, target, monster_id, completed) VALUES ('I think i can be killed so easily', 5, 11, false);
INSERT INTO QUEST(name, target, boss_id, completed) VALUES ('Good luck, if you dont die, you will receive a cookie', 1, 2, false);

INSERT INTO NPC(name, job) VALUES ('Mayara the Witch', 'Adventurer');
INSERT INTO NPC(name, job) VALUES ('John', 'Adventurer');
INSERT INTO NPC(name, job) VALUES ('Kohaku', 'Assassin');
INSERT INTO NPC(name, job) VALUES ('Elisa', 'Queen');
INSERT INTO NPC(name, job) VALUES ('Marcos', 'Merchant');
INSERT INTO NPC(name, job) VALUES ('Bugnmy', 'Friendly Goblin');
INSERT INTO NPC(name, job) VALUES ('Goblin Slayer', 'Slayer of Goblins');
INSERT INTO NPC(name, job) VALUES ('Shouko', 'Friendly Goblin');
INSERT INTO NPC(name, job) VALUES ('Bertamel', 'Blacksmith');
INSERT INTO NPC(name, job) VALUES ('Olavo', 'Storager');
INSERT INTO NPC(name, job) VALUES ('Kazuto', 'Adventurer');

INSERT INTO PLAYER_QUESTS(player_id, quests_id) VALUES (1,1);