import Observer.Logger;
import Observer.Tracker;

public class Singleton {
    public class Singleton {
        //    private static int day;
        private static final Tracker track = new Tracker();//eager initialization
        private static volatile Logger log = null;
        private Singleton()//blocking other classes from creating this class
        {

        }
        public static Tracker getInstanceTracker()//eager initialization
        {
            return track;
        }
        public static Logger getInstanceLogger(int day)//lazy initialization thread safe
        {
            System.out.println(day+"*?//");
            Logger l = log;
            if (l == null)
            {
                synchronized(Singleton.class){
                    l=log;
                    if(l ==null)
                    {
                        l= new Logger(day);
                        log=null;
                    }
                }
            }

            return l;
        }
    }
}
