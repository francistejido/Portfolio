/**
 * Greek Mythology Fortune Teller Program
 * <p>
 * An application that predicts a user's mythical destiny based on their inputs.
 * Prompts for name, favorite number, birth month, and belief in fate, then assigns:
 * - A career, relationship, pet and travel destination - all inspired by Greek mythology.
 * Features input validation, themed enum options, and descriptive narrative outputs.
 * </p>
 * <p>
 * Uses java.util.Scanner for user input.
 * </p>
 *
 * @author Francisco Tejido
 * @version 1.0
 * @since 2025-09-28
 */
package ca.qc.cegepheritage.g10a1;

import java.util.Scanner;
import java.io.*;

public class Main {
    // ********** ENUM Declarations *********

    enum CareerOptions {
        ATHENA, APOLLO, ARTEMIS, DEMETER, HERMES, APHRODITE, HEPHAESTUS, ZEUS, HESTIA, POSEIDON
    }

    enum RelationshipOptions {
        DIVINE_PAIRING, MORTAL_AND_GOD, MENTOR_AND_MUSE, UNDERWORLD_ROMANCE, TRICKSTER_BONDS, ETERNAL_FRIENDSHIP, FATED_LOVERS, RIVALRY_ROMANCE, SACRED_OATHS, EPIC_PARENT_CHILD
    }

    enum PetOptions {
        CERBERUS, PEGASUS, PHOENIX, CHIMERA, GRIFFIN, HYDRA, ORTHRUS, HIPPALOS, OWL_OF_ATHENA, NEMEAN_LION
    }

    enum TravelOptions {
        MOUNT_OLYMPUS, DELPHI, TROY, KNOSSOS, ITHACA, ACHERON_RIVER, NEMEA, ARCADIA, KYTHIRA, DELOS
    }

    //Font and Background color + Font Format
    public static final String Font_Reset = "\u001B[0m";
    public static final String Font_Cyan = "\u001B[36m";
    public static final String Font_Black = "\u001B[30m";
    public static final String BG_Blue = "\u001B[44m";
    public static final String Font_Yellow = "\u001B[33m";
    public static final String Font_Red = "\u001B[31m";
    public static final String Font_Italic = "\u001B[3m";

    public static void main(String[] args) {

        //Mystical Oracle - a fortune-teller program based on Greek Mythology
        //Inputs and Conditions
        Scanner input = new Scanner(System.in);

        System.out.println(" ");
        System.out.println(Font_Black + BG_Blue + "  Welcome, Seeker, to the Oracle's Veil of Wisdom!" + Font_Reset);
        System.out.println(" ");

        System.out.print(Font_Cyan + "Enter your name [A-Z]: ");
        String name = input.nextLine();

        if (!name.matches("[a-zA-Z]*")) {
            System.out.println(Font_Red + "You have faced the wrath of the Gods! Name must be between characters A and Z.");
            System.out.println("Avtio." + Font_Reset); //Avtio - Greek for goodbye.
            System.exit(0);
        }

        System.out.print("Enter your favourite number [1-10]: ");
        int faveNumber = input.nextInt();

        if (faveNumber <= 0 || faveNumber > 10) {
            System.out.println(Font_Red + "You have faced the wrath of the Gods! Favourite number must be between 1 and 10.");
            System.out.println("Avtio." + Font_Reset);
            System.exit(0);
        }

        System.out.print("Enter your birth month [1-12]: ");
        int birthMonth = input.nextInt();

        if (birthMonth <= 0 || birthMonth > 12) {
            System.out.println(Font_Red + "You have faced the wrath of the Gods! Birth month must be between 1 and 12.");
            System.out.println("Avtio." + Font_Reset);
            System.exit(0);
        }

        System.out.print("Do you believe in fate? [yes/no]: ");
        String inputFate = input.next().toLowerCase();

        boolean fate = false;
        if (inputFate.equals("yes")) { //Condition to convert "yes" to true
            fate = true;
        } else if (inputFate.equals("no")) { //Condition to convert "no" to false
            fate = false;
        } else {
            System.out.println(Font_Red + "You have faced the wrath of the Gods! Only yes or no responses are accepted. ");
            System.out.println("Avtio." + Font_Reset);
            System.exit(0);
        }

        //Switches and filters

        //CareerOptions
        CareerOptions[] optionCareer = CareerOptions.values();
        CareerOptions careerOptions;
        int career = (faveNumber + birthMonth) % optionCareer.length; //Get the modulo of the sum of favorite number and birth month based on the number of career options
        careerOptions = optionCareer[career];

        String printCareerOptions = " ";
        switch (careerOptions) {
            case ATHENA:
                printCareerOptions = "Blessed by Athena, the goddess of wisdom. \nYour path leads to triumph in the realms of teaching, justice, and scholarship.";
                break;
            case APOLLO:
                printCareerOptions = "You are blessed by Apollo, radiant god of the sun. \nYou will be successful in the fields of music, medicine, and teaching.";
                break;
            case ARTEMIS:
                printCareerOptions = "You are guided by Artemis, goddess of the hunt. \nYou are marked for success as a wildlife conservationist.";
                break;
            case DEMETER:
                printCareerOptions = "You are favored by Demeter, the goddess of the harvest. \nYou will be successful in agricultural science, nutrition, and botany.";
                break;
            case HERMES:
                printCareerOptions = "You are blessed by Hermes, swift-footed messenger of Olympus. \nYou are poised for triumph as an entrepreneur, a journalist or a diplomat.";
                break;
            case APHRODITE:
                printCareerOptions = "Anointed by Aphrodite, radiant goddess of love and beauty. \nYou thrive as a visionary artist or a celebrated designer.";
                break;
            case HEPHAESTUS:
                printCareerOptions = "Endowed with the favor of Hephaestus, master of fire and forge. \nYour talents flourish as an engineer, artisan, or inventor.";
                break;
            case ZEUS:
                printCareerOptions = "Chosen by Zeus, mighty ruler of Olympus and bringer of thunder. \nSuccess awaits you as a president, judge, executive, or manager.";
                break;
            case HESTIA:
                printCareerOptions = "Graced by Hestia, gentle guardian of hearth and home. \nYour spirit flourishes in roles like family therapist, or hospitality expert.";
                break;
            case POSEIDON:
                printCareerOptions = "Blessed by Poseidon, sovereign of the seas and shaker of the earth. \nYour success flows through the currents of marine biology, or ship navigation.";
                break;
        }

        //RelationshipOptions
        RelationshipOptions[] optionRelationships = RelationshipOptions.values();
        RelationshipOptions relationshipOptions;
        int randomNumberCareer;
        if (fate == true) {
            randomNumberCareer = (int) (Math.random() * optionRelationships.length); //If fate is true, pick a random index within RelationshipOptions
            relationshipOptions = optionRelationships[randomNumberCareer]; // Assign a random relationship option from the array
        } else {
            randomNumberCareer = faveNumber % optionRelationships.length; //If fate is false, use favorite number modulo the array to stay within range
            relationshipOptions = optionRelationships[randomNumberCareer];
        }

        String printRelationshipOptions = " ";
        switch (relationshipOptions) {
            case DIVINE_PAIRING:
                printRelationshipOptions = "Divine Pairing - like Zeus and Hera. \nYour partnerships are powerful and sometimes dramatic.";
                break;
            case MORTAL_AND_GOD:
                printRelationshipOptions = "Mortal and God - like Perseus and Andromeda. \nYour relationships are characterized by rescue, partnership and growth.";
                break;
            case MENTOR_AND_MUSE:
                printRelationshipOptions = "Mentor and Muse - like Athena mentoring heroes. \nYour relationships are destined to thrive in roles of mentorship.";
                break;
            case UNDERWORLD_ROMANCE:
                printRelationshipOptions = "Underworld Romance - like Hades and Persephone. \nYou have love that defies boundaries, flourishing even when tested by distance.";
                break;
            case TRICKSTER_BONDS:
                printRelationshipOptions = "Trickster Bonds - like Hermes and Aphrodite. \nYou have mischievous alliances that weaves laughter and surprise to people.";
                break;
            case ETERNAL_FRIENDSHIP:
                printRelationshipOptions = "Eternal Friendship - like Achilles and Patroclus. \nYou have unwavering loyalty, forging bonds that endure tough trials.";
                break;
            case FATED_LOVERS:
                printRelationshipOptions = "Fated Lovers - like Orpheus and Eurydice. \nYour partnerships are united through adversity, and the workings of fate.";
                break;
            case RIVALRY_ROMANCE:
                printRelationshipOptions = "Rivalry Romance - like Ares and Aphrodite. \nYour relationship dynamics are tethered by both fierce conflict and passion.";
                break;
            case SACRED_OATHS:
                printRelationshipOptions = "Sacred Oaths - like the heroes and the gods. \nYour relationships are bonded by solemn vows and promises, bound by honor.";
                break;
            case EPIC_PARENT_CHILD:
                printRelationshipOptions = "Epic Parent-Child - like Demeter and Persephone. \nYou have deep, formative family ties that shape destinies through nurturing.";
                break;
        }

        //PetOptions
        PetOptions[] optionPets = PetOptions.values();
        PetOptions petOptions;
        int randomNumberPet = (int) (Math.random() * optionPets.length); //Pick a random index from the number of pet options available
        petOptions = optionPets[randomNumberPet];

        String printPetOptions = " ";
        switch (petOptions) {
            case CERBERUS:
                printPetOptions = "Cerberus - the three-headed dog of Hades. \nYour pet is fiercely loyal and offers ultimate protection and companionship.";
                break;
            case PEGASUS:
                printPetOptions = "Pegasus - the winged horse. \nThis pet is a devoted companion and offers magical means of travel.";
                break;
            case PHOENIX:
                printPetOptions = "Phoenix - the immortal firebird reborn from its ashes. \nThis pet can bring warmth, inspiration and rebirth after hard times.";
                break;
            case CHIMERA:
                printPetOptions = "Chimera - the fire breathing lion-goat-serpent hybrid. \nThis pet is an extraordinary and strong-willed guardian.";
                break;
            case GRIFFIN:
                printPetOptions = "Griffin - lion and eagle hybrid. \nA majestic and protective pet; a perfect regal companion.";
                break;
            case HYDRA:
                printPetOptions = "Hydra - muli-headed serpent. \nAn intriguing and clever pet, perfect for outsmarting daily challenges.";
                break;
            case ORTHRUS:
                printPetOptions = "Orthrus - two-headed guard dog. \nA watchful and energetic pet, ideal for home or farm life.";
                break;
            case HIPPALOS:
                printPetOptions = "Hippalos - Poseidon's immortal horse. \nA pet perfect for long journeys and showing unmatched loyalty.";
                break;
            case OWL_OF_ATHENA:
                printPetOptions = "Owl of Athena - symbol of wisdom. \nA wise pet offering inspiration and guidance in daily life.";
                break;
            case NEMEAN_LION:
                printPetOptions = "Nemean lion - the invulnerable lion. \nThis pet will defend you bravely and be a powerful presence.";
                break;
        }

        //TravelOptions
        TravelOptions[] optionTravel = TravelOptions.values();
        TravelOptions travelOptions;
        int travel = (birthMonth) % optionTravel.length; // Use birth month and get modulo based on the number of travel options
        travelOptions = optionTravel[travel];

        String printTravelOptions = " ";
        switch (travelOptions) {
            case MOUNT_OLYMPUS:
                printTravelOptions = "Mount Olympus - legendary home of the gods. \nYou're going to a top hiking destination and symbol of achievement.";
                break;
            case DELPHI:
                printTravelOptions = "Delphi - ancient oracle's seat. \nYou'll seek wisdom and self-discovery in beautiful surroundings.";
                break;
            case TROY:
                printTravelOptions = "Troy - site  of epic battles in the Iliad. \nYou're going to discover ruins and lessons in resilience.";
                break;
            case KNOSSOS:
                printTravelOptions = "Knossos - palace of the Minotaur. \nYou'll wander complex ruins, experiencing history's mysteries and adventure.";
                break;
            case ITHACA:
                printTravelOptions = "Ithaca - home to Odysseus. \nYou're up for a homecoming and the thrill of island escapes.";
                break;
            case ACHERON_RIVER:
                printTravelOptions = "Acheron River - mythic river to the Underworld. \nYou'll enjoy a scenic site for rafting while reflecting on new beginnings.";
                break;
            case NEMEA:
                printTravelOptions = "Nemea - land of Hercules. \nBe ready to enjoy its famed wine and ancient festival spirit.";
                break;
            case ARCADIA:
                printTravelOptions = "Arcadia - realm of rural peace and Pan. \nThis is a nature lover's paradise perfect for those seeking tranquility.";
                break;
            case KYTHIRA:
                printTravelOptions = "Kythira - birthplace of Aphrodite. \nYou're in for a romantic getaway.";
                break;
            case DELOS:
                printTravelOptions = "Delos - sacred island of Apollo and Artemis. \nYou'll enjoy a hub of culture and vibrant heritage.";
                break;
        }

        //Display text and values based on the computations and conditions above
        System.out.println(" ");
        System.out.println(Font_Cyan + Font_Italic + "  Weaving your destiny among the constellations...");
        System.out.println(" ");
        System.out.println("*** Your future as decreed by the gods ***" + Font_Reset);
        System.out.println(" ");
        System.out.println(Font_Yellow + name + Font_Cyan + ", the thread spun by the Fates reveals: ");
        System.out.println(Font_Yellow + "1. Career: " + Font_Cyan + printCareerOptions);
        System.out.println(Font_Yellow + "2. Relationship: " + Font_Cyan + printRelationshipOptions);
        System.out.println(Font_Yellow + "3. Pet: " + Font_Cyan + printPetOptions);
        System.out.println(Font_Yellow + "4. Travel Destination: " + Font_Cyan + printTravelOptions);
        System.out.println(" ");
        System.out.println(Font_Cyan + Font_Italic + " May the Fates entwine your path as the gods array the stars, ");
        System.out.println("         illuminating fortune upon your journey...");
        System.out.println(" ");
        System.out.println(" ************************************************************");


    }
}