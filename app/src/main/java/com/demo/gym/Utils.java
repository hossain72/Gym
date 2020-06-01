package com.demo.gym;

import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class Utils {

    private static List<Training> trainingList;
    private static List<Plan> planList;

    public static void initTraining() {

        if (null == trainingList) {
            trainingList = new ArrayList<>();

            trainingList.add(new Training(1, "Push Up", "A push-up is a common calisthenics exercise beginning from the prone position",
                    "A push-up (or press-up if the hands are wider than shoulders placing more emphasis on the pectoral muscles) is a common calisthenics exercise beginning from the prone position. By raising and lowering the body using the arms, push-ups exercise the pectoral muscles, triceps, and anterior deltoids, with ancillary benefits to the rest of the deltoids, serratus anterior, coracobrachialis and the midsection as a whole. Push-ups are a basic exercise used in civilian athletic training or physical education and commonly in military physical training. They are also a common form of punishment used in the military, school sport, or some martial arts disciplines.",
                    "https://upload.wikimedia.org/wikipedia/commons/thumb/b/ba/Planche.jpg/1920px-Planche.jpg"));

            trainingList.add(new Training(2, "Squat", "The Squat is a lower body exercise. You can do the bodyweight version, without added resistance, or with weights such as a barbell.",
                    "A squat is a strength exercise in which the trainee lowers their hips from a standing position and then stands back up. During the descent of a squat, the hip and knee joints flex while the ankle joint dorsiflexes; conversely the hip and knee joints extend and the ankle joint plantarflexes when standing up.\n" +
                            "\n" +
                            "Squats are considered a vital exercise for increasing the strength and size of the lower body muscles as well as developing core strength. The primary agonist muscles used during the squat are the quadriceps femoris, the adductor magnus, and the gluteus maximus.[1] The squat also isometrically uses the erector spinae and the abdominal muscles, among others.[2]\n" +
                            "\n" +
                            "The squat is one of the three lifts in the strength sport of powerlifting, together with the deadlift and the bench press. It is also considered a staple exercise in many popular recreational exercise programs. ",
                    "https://www.mensjournal.com/wp-content/uploads/2018/02/squats-mens-journal-february-2018.jpg"));

            trainingList.add(new Training(3, "Leg Press", "The leg press is a weight training exercise in which the individual pushes a weight or resistance away from them using their legs",
                    "The leg press is a weight training exercise in which the individual pushes a weight or resistance away from them using their legs. The term leg press also refers to the apparatus used to perform this exercise. The leg press can be used to evaluate an athlete's overall lower body strength (from knee joint to hip). It has the potential to inflict grave injury: the knees could bend the wrong way if they are locked during a leg press.",
                    "https://cdn1.coachmag.co.uk/sites/coachmag/files/legpress1.png"));

            trainingList.add(new Training(4, "Pectorals", "Amazing for building chest muscles", "" +
                    "Pectoral muscles are the muscles that connect the front of the human chest with the bones of the upper arm and shoulder.",
                    "https://st3.depositphotos.com/8742290/16820/i/1600/depositphotos_168207582-stock-photo-brutal-caucasian-bodybuilder-training-chest.jpg"));

            trainingList.add(new Training(5, "Pull Up", "A pull-up is an upper-body strength exercise", "" +
                    "A pull-up is an upper-body strength exercise. The pull-up is a closed-chain movement where the body is suspended by the hands and pulls up. As this happens, the elbows flex and the shoulders adduct and extend to bring the elbows to the torso.",
                    "https://www.mirafit.co.uk/wp/wp-content/uploads/sites/2/2019/05/Mirafit_blog_from_pull_ups_to_muscle_ups_picture_of_man_deadhanging_from_a_bar.jpg"));

        }

    }

    public static List<Training> getTrainingList() {
        return trainingList;
    }


    public static boolean addPlan(Plan plan) {

        if (null == planList) {
            planList = new ArrayList<>();
        }

        return planList.add(plan);
    }

    public static List<Plan> getPlanList() {
        return planList;
    }

    public static boolean removePlan(Plan plan){
        return planList.remove(plan);
    }
}
