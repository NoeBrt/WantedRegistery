package DAO;

public final class DBContract {

    public static class Form {
        public static final String TABLE_NAME = "wanted";

        public static final String _ID = "id";

        public static final String COLUMN_PHOTO = "photo";

        public static final String COLUMN_NAME = "name";

        public static final String COLUMN_SUBJECT = "subject";

        public static final String COLUMN_UID = "uid" ;

        public static final String COLUMN_DATE = "dateOfBirth";

        public static final String COLUMN_AGE = "age";

        public static final String COLUMN_HAIR = "hair";

        public static final String COLUMN_EYES = "eyes";

        public static final String COLUMN_HEIGHT = "height";

        public static final String COLUMN_SEX = "sex";

        public static final String COLUMN_RACE = "race";

        public static final String COLUMN_NATIONALITY = "nationality";

        public static final String COLUMN_MARKS = "scarsAndMarks";

        public static final String COLUMN_NCIC = "ncic";

        public static final String COLUMN_REWARD = "reward";

        public static final String COLUMN_ALIASES = "aliases";

        public static final String COLUMN_REMARKS = "remarks";

        public static final String COLUMN_CAUTION = "caution";

        public static final String COLUMN_WEIGHT = "weight";

        //Tables des images détaillées
        public static final String TABLE_NAME2 = "images";

        public static final String COLUMN_IMAGE = "image";
    }
}
